package model

fun <Object> Map<Vector2d, Object>.randomPosition(): Vector2d? {
    return keys.toList().randomOrNull()
}
fun <Object> Map<Vector2d, Object>.randomFreePosition(mapSize: Vector2d): Vector2d? {
    val x = mapSize.x
    val y = mapSize.y
    val allPositions = mutableSetOf<Vector2d>()
    for(i in 0..x) {
        for(j in 0..y) {
            allPositions.add(Vector2d(i,j))
        }
    }
    val occupiedPositions = keys.toList().toSet()
    if(allPositions == occupiedPositions) return null
    return (allPositions - occupiedPositions).random()
}