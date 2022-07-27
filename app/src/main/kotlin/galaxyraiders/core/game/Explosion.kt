package galaxyraiders.core.game

import galaxyraiders.core.physics.Point2D
import galaxyraiders.core.physics.Vector2D

class Explosion (

    // Se a explosao ja aconteceu
    // var exploded = false,
    position: Point2D,
    initialVelocity: Vector2D,
    radius: Double,
    mass: Double
    
) : 
    SpaceObject("Explosion", '*', position, initialVelocity, radius, mass)