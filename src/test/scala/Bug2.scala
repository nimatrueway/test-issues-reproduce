import org.scalatest.BeforeAndAfterEach
import org.scalatest.wordspec.AsyncWordSpec

class Bug2 extends AsyncWordSpec with BeforeAndAfterEach {

  override def beforeEach(): Unit = {
    throw new Error("some error!")
  }

  "BeforeAndAfterEach in AsyncWordSpec" should {
    "should give user a clear failure error when an exception is thrown in beforeEach()" in {
      fail
    }
  }

}
