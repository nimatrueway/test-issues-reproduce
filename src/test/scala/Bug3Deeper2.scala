import org.scalatest.matchers
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import software.amazon.awssdk.services.rekognition.RekognitionClient
import software.amazon.awssdk.services.rekognition.model.DetectLabelsRequest

import java.lang.reflect.Method
import scala.language.reflectiveCalls
import scala.reflect.internal.Symbols
import scala.util.Failure
import scala.util.Success
import scala.util.Try

class Bug3Deeper2 extends AnyWordSpec with Matchers {

  import org.mockito.ReflectionUtils._

  import scala.reflect.runtime.universe

  /** A copy of mockito-scala_2.12-1.16.29.jar!/org/mockito/ReflectionUtils.scala
    */
  class ReflectionUtils(method: Method) {
    private val mirror = universe.runtimeMirror(getClass.getClassLoader)
    private val customMirror = mirror.asInstanceOf[{
        def methodToJava(sym: Symbols#MethodSymbol): Method
      }
    ]

    private def isNonConstructorMethod(d: universe.Symbol): Boolean =
      d.isMethod && !d.isConstructor

    /** If scala symbol of our java method
      * @return
      */
    private def findTypeSymbol =
//      scala.util
//        .Try {
      mirror
        .classSymbol(method.getDeclaringClass)
        .info
        .decls
        .collectFirst {
          case symbol
              if isNonConstructorMethod(symbol) && customMirror
                .methodToJava(symbol) === method =>
            symbol
        }
//        }
//        .toOption
//        .flatten

    def returnsValueClass: Boolean =
      findTypeSymbol.exists(_.returnType.typeSymbol.isDerivedValueClass)
  }

  "Mockito-Scala - returnsValueClass" should {
    def subjectMethod = classOf[RekognitionClient].getDeclaredMethod(
      "detectLabels",
      classOf[DetectLabelsRequest]
    )
    def doesReturnCaseClass: Boolean =
      new ReflectionUtils(subjectMethod).returnsValueClass

    "determine if a mocked method returns a case-class instance" in {
      val first = Try {
        doesReturnCaseClass
      }

      val second = Try {
        doesReturnCaseClass
      }

      first shouldBe second
    }
  }

}
