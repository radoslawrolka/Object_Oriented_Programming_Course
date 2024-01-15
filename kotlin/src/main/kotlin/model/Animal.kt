package model

class Animal(private var orientation: MapDirection = MapDirection.NORTH, private var position: Vector2d = Vector2d(2,2)) {

    override fun toString() = orientation.toString()

    fun isAt(position: Vector2d) = this.position == position

    fun getPosition() = this.position

    fun setPosition(position: Vector2d) {
        this.position = position
    }

    fun move(direction: MoveDirection) = when(direction) {
        MoveDirection.RIGHT -> this.orientation = orientation.next()
        MoveDirection.LEFT -> this.orientation = orientation.previous()
        MoveDirection.FORWARD -> this.position += this.orientation.toUnitVector()
        MoveDirection.BACKWARD -> this.position -= this.orientation.toUnitVector()
    }
}