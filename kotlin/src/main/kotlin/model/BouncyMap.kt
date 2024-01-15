package model

class BouncyMap(private var width: Int, private var height: Int) : IWorldMap {
    private val map = HashMap<Vector2d, Animal>()

    override val id = this.hashCode()

    override val getElements: ArrayList<Animal> get() = ArrayList(map.values)

    override fun isOccupied(position: Vector2d) = (objectAt(position) != null)

    fun canMoveTo(position: Vector2d) = (position follows Vector2d(0,0) && position precedes Vector2d(width,height))

    override fun objectAt(position: Vector2d) = map[position]

    override fun move(animal: Animal, direction: MoveDirection) {
        val oldPosition = animal.getPosition()
        val oldOrientation = animal.toString()
        animal.move(direction)
        if(canMoveTo(animal.getPosition())) {
            if(animal.getPosition() != oldPosition) {
                map.remove(oldPosition)
                map[animal.getPosition()] = animal
            } else if(oldOrientation != animal.toString()) {
                map[animal.getPosition()] = animal
            }
        }
    }

    override fun place(animal: Animal) {
        val position = animal.getPosition()
        if(map.containsValue(animal)) {
            val oldPosition = map.filterValues { it == animal }.keys.first()
            map.remove(oldPosition)
            map[position] = animal
        } else if(canMoveTo(position)) {
            if(objectAt(position) == null) {
                map[position] = animal
            } else if(map.values.size == width * height){
                val randomPosition = map.randomPosition()
                map.remove(randomPosition)
                map[position] = animal
            } else {
                val freePosition = map.randomFreePosition(Vector2d(width,height))
                if(freePosition != null) {
                    map[freePosition] = animal
                    animal.setPosition(freePosition)
                }
            }
        }
    }
}