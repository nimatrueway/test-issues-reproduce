import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.language.reflectiveCalls
import scala.util.Try

/**  We need to figure why Scala Reflection caches partially extracted symbols
  *  Interesting points to trace and analyze:
  *
  *  JavaMirrors.scala:791
  *  JavaMirrors.scala:827
  *  TwoWayCaches.scala:34
  *  scala/reflect/internal/Symbols.scala:3762
  */
class Bug3Deeper4 extends AnyWordSpec with Matchers {

  import scala.reflect.runtime.universe

  private def createMirror = universe.runtimeMirror(getClass.getClassLoader)

  "Scala Reflection - ClassSymbol.info.decls" should {

    "consistently either fail or return a set of methods" in {
      val clazz = classOf[Z]

      val first = Try {
        createMirror.classSymbol(clazz).info.decls
      }

      val second = Try {
        createMirror.classSymbol(clazz).info.decls
      }

      assert(second == first)
      println(first)
    }
  }

}
