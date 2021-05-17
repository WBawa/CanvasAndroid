class Hippo : Animal() {
   override val image: String = "Hippo.png"
   override val food: String = "plants"
   override val habitat: String = "water"

   override fun makeNoise() {
        println("The Hippo is making a noise")
   }

   override fun eat() {
       println("The Hippo is eating")
   }

   override fun roam() {
       println("The Hippo is roaming")
   }

}
