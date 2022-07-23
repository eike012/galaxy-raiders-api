@file:Suppress("UNUSED_PARAMETER") // <- REMOVE
package galaxyraiders.core.physics

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties("unit", "normal", "degree", "magnitude")
data class Vector2D(val dx: Double, val dy: Double) {
  override fun toString(): String {
    return "Vector2D(dx=$dx, dy=$dy)"
  }

  val magnitude: Double
    get() = Math.sqrt(dx * dx + dy * dy)

  val radiant: Double
    get() = {
      if(dy / magnitude < 0){
        return -1.0 * Math.abs(Math.acos(dx / magnitude))
      }

      return Math.abs(Math.acos(dx / magnitude))
    }

  val degree: Double
    get() = (180 * radiant) / Math.PI

  val unit: Vector2D
    get() = Vector2D(dx / magnitude, dy / magnitude)

  val normal: Vector2D
    get() = Vector2D(dy / magnitude, - (dx/ magnitude))

  operator fun times(scalar: Double): Vector2D {
    return Vector2D(dx * scalar, dy * scalar)
  }

  operator fun div(scalar: Double): Vector2D {
    return Vector2D(dx / scalar, dy / scalar)
  }

  operator fun times(v: Vector2D): Double {
    return ((v.dx * dx) + (v.dy * dy))
  }

  operator fun plus(v: Vector2D): Vector2D {
    return Vector2D(v.dx + dx, v.dy + dy)
  }

  operator fun plus(p: Point2D): Point2D {
    return Point2D(p.x + dx, p.y + dy)
  }

  operator fun unaryMinus(): Vector2D {
    return Vector2D((-1.0) * dx, (-1.0) * dy)
  }

  operator fun minus(v: Vector2D): Vector2D {
    return Vector2D(dx - v.dx, dy - v.dy)
  }

  fun scalarProject(target: Vector2D): Double {
    return INVALID_DOUBLE
  }

  fun vectorProject(target: Vector2D): Vector2D {
    return INVALID_VECTOR
  }
}

operator fun Double.times(v: Vector2D): Vector2D {
  return Vector2D(v.dx * this, v.dy * this)
}
