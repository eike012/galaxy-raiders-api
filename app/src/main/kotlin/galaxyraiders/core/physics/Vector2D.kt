package galaxyraiders.core.physics

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

private const val halfCircle = 180

@JsonIgnoreProperties("unit", "normal", "degree", "magnitude")
data class Vector2D(val dx: Double, val dy: Double) {
  override fun toString(): String {
    return "Vector2D(dx=$dx, dy=$dy)"
  }

  val magnitude: Double
    get() = Math.sqrt(dx * dx + dy * dy)

  // atan2 retorna o arco cuja tangente
  // e o angulo entre o cateto dy e dx
  val radiant: Double
    get() = Math.atan2(dy, dx)

  val degree: Double
    get() = (halfCircle * radiant) / Math.PI

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

    return (target.dx * dx + target.dy * dy) / target.magnitude
  }

  fun vectorProject(target: Vector2D): Vector2D {
    return (((target.dx * dx + target.dy * dy) / target.magnitude) * target.unit)
  }
}

operator fun Double.times(v: Vector2D): Vector2D {
  return Vector2D(v.dx * this, v.dy * this)
}
