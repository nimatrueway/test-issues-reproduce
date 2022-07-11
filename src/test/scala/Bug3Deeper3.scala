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

  private val subjectInterface = classOf[RekognitionClient]

  "Scala Reflection - ClassSymbol.info.decls" should {

    def allMethods = {
      val clazzSymbol = mirror.classSymbol(subjectInterface)
      val clazzInfo = clazzSymbol.info
      clazzInfo.decls
    }

    "consistently either fail or return a set of methods" in {
      val first = Try {
        allMethods
      }

      val second = Try {
        allMethods
      }

      first shouldBe second
    }
  }

}
