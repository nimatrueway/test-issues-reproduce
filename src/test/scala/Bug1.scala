import org.scalamock.scalatest.MockFactory
import org.scalatest.wordspec.AsyncWordSpec

import scala.concurrent.Future

/** MockFactory trait of AsyncWordSpec doesn't play well with AsyncWordSpec
  */
class Bug1 extends AsyncWordSpec with MockFactory {

  "MockFactory of ScalaMock" should {
    "not override AsyncWordSpec's runTest()" in {
      Future {
        fail("This test must fail!")
      }
    }
  }

}
