import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import software.amazon.awssdk.services.rekognition.RekognitionClient
import software.amazon.awssdk.services.rekognition.model.DetectLabelsRequest

import java.lang.reflect.Method
import scala.language.reflectiveCalls
import scala.util.Try

class Bug3Deeper3 extends AnyWordSpec with Matchers {

  import scala.reflect.runtime.universe

  private def mirror = universe.runtimeMirror(getClass.getClassLoader)

  private val subjectMethod = classOf[RekognitionClient].getDeclaredMethod(
    "detectLabels",
    classOf[DetectLabelsRequest]
  )

  "Scala Reflection - ClassSymbol.info.decls" should {

    def allMethods(method: Method) =
      mirror
        .classSymbol(method.getDeclaringClass)
        .info
        .decls

    "consistently either fail or return a set of methods" in {
      val first = Try {
        allMethods(subjectMethod)
      }

      val second = Try {
        allMethods(subjectMethod)
      }

      first shouldBe second
    }
  }

}
