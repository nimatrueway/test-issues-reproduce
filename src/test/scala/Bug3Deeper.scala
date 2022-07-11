import org.mockito.invocation.InvocationOnMock
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import software.amazon.awssdk.services.rekognition.RekognitionClient
import software.amazon.awssdk.services.rekognition.model.DetectLabelsRequest

import java.lang.reflect.Method
import scala.language.reflectiveCalls

class Bug3Deeper extends AnyWordSpec with Matchers {

  def doesReturnValueClass(): Boolean = {
    import org.mockito.ReflectionUtils._

    val method = classOf[RekognitionClient].getDeclaredMethod(
      "detectLabels",
      classOf[DetectLabelsRequest]
    )
    val fakeInvocation = new InvocationOnMock {
      override def getMock: AnyRef = ???
      override def getMethod: Method = method
      override def getArguments: Array[AnyRef] = ???
      override def getArgument[T](index: Int): T = ???
      override def getArgument[T](index: Int, clazz: Class[T]): T = ???
      override def callRealMethod(): AnyRef = ???
    }

    fakeInvocation.returnsValueClass
  }

  "Mockito-Scala - returnsValueClass" should {
    "determine if a mocked method returns a case-class instance" in {
      doesReturnValueClass() shouldBe doesReturnValueClass()
    }
  }

}
