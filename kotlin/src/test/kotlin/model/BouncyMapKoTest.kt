package model

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class BouncyMapKotest: ShouldSpec ({
    should("canMoveTo should return true when position is within map boundaries") {
        val map = BouncyMap(5, 5)
        val position1 = Vector2d(3, 3)
        val position2 = Vector2d(0, 0)
        val position3 = Vector2d(5, 5)

        map.canMoveTo(position1) shouldBe true
        map.canMoveTo(position2) shouldBe true
        map.canMoveTo(position3) shouldBe true
    }

    should("canMoveTo should return false when position is outside map boundaries") {
        val map = BouncyMap(5, 5)
        val position1 = Vector2d(-1, 3)
        val position2 = Vector2d(3, -1)
        val position3 = Vector2d(6, 3)
        val position4 = Vector2d(3, 6)

        map.canMoveTo(position1) shouldBe false
        map.canMoveTo(position2) shouldBe false
        map.canMoveTo(position3) shouldBe false
        map.canMoveTo(position4) shouldBe false
    }

    should("isOccupied should return true when position is occupied by an animal") {
        val map = BouncyMap(5, 5)
        val animal = Animal(position = Vector2d(3, 3))
        map.place(animal)
        val position = Vector2d(3, 3)

        map.isOccupied(position) shouldBe true
    }

    should("isOccupied should return false when position is not occupied by any animal") {
        val map = BouncyMap(5, 5)
        val position = Vector2d(3, 3)

        map.isOccupied(position) shouldBe false
    }

    should("objectAt should return the animal at the given position") {
        val map = BouncyMap(5, 5)
        val animal = Animal(position = Vector2d(3, 3))
        map.place(animal)
        val position = Vector2d(3, 3)

        map.objectAt(position) shouldBe animal
    }
    should("objectAt should return null when there is no animal at the given position") {
        val map = BouncyMap(5, 5)
        val position = Vector2d(3, 3)

        map.objectAt(position) shouldBe null
    }
    should("move should move animal to the given position") {
        val map = BouncyMap(5, 5)
        val animal = Animal(position = Vector2d(3, 3))
        map.place(animal)
        val position = Vector2d(3, 4)

        map.move(animal, MoveDirection.FORWARD)
        animal.getPosition() shouldBe position
        map.objectAt(position) shouldBe animal

        val position2 = Vector2d(4, 4)
        map.move(animal, MoveDirection.RIGHT)
        map.move(animal, MoveDirection.FORWARD)
        animal.getPosition() shouldBe position2
        map.objectAt(position2) shouldBe animal
        map.objectAt(position) shouldBe null
    }
    should("place should place animal at the given position") {
        val map = BouncyMap(5, 5)
        val animal = Animal(position = Vector2d(3, 3))
        map.place(animal)
        val position = Vector2d(3, 3)

        animal.getPosition() shouldBe position
        map.objectAt(position) shouldBe animal

        val position2 = Vector2d(4, 4)
        animal.setPosition(position2)
        map.place(animal)
        animal.getPosition() shouldBe position2
        map.objectAt(position2) shouldBe animal
        map.objectAt(position) shouldBe null
    }
})