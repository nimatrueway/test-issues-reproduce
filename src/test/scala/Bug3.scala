import org.mockito.ArgumentMatchersSugar.any
import org.mockito.MockitoSugar.mock
import org.mockito.MockitoSugar.when
import org.scalatest.wordspec.AnyWordSpec
import software.amazon.awssdk.services.rekognition.RekognitionClient
import software.amazon.awssdk.services.rekognition.model.DetectLabelsRequest
import software.amazon.awssdk.services.rekognition.model.DetectLabelsResponse
import software.amazon.awssdk.services.rekognition.model.Label

class Bug3 extends AnyWordSpec {

  private val aResponse = {
    val aLabel = Label.builder().confidence(99.0f).name("the label").build()
    DetectLabelsResponse.builder().labels(aLabel).build()
  }

  "Mockito-Scala" should {
    "is not capable of reliably mocking RekognitionClient (run me in an openjdk container)" in {
      val rekognitionClientMock = mock[RekognitionClient]
      when(rekognitionClientMock.detectLabels(any[DetectLabelsRequest]))
        .thenReturn(aResponse)
    }
  }

}
