class Wolf : Animal() {
   override val image: String = "Wolf.png"
   override val food: String = "meat"
   override val habitat: String = "land"

   override fun makeNoise() {
        println("The Wolf is making a noise")
   }

   override fun eat() {
       println("The Wolf is eating")
   }

   override fun roam() {
       println("The Wolf is roaming")
   }
}
