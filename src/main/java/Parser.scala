

object Parser {
   def main(args: Array[String]) {

    // on donne le chemain d'accees en tant que argument
    val bufferedSource = io.Source.fromFile(args(0))

    //intialisation des variables
    var couple: List[String] = List.empty[String]
    var view_type: String = ""
    var count: Int = 0
    var zoom: List[String] = List.empty[String]

    //lecture du fichier ligne par ligne
    for (line <- bufferedSource.getLines) {
 try {
      // verfication que la ligne corrrespond au paterne
      if (line.split(" ")(2).startsWith("/map/1.0/slab/")) {

        //extraction des valeurs viewmode et niveau de zoom
        couple = line.split(" ")(2).split("/")(4) :: line.split(" ")(2).split("/")(6) :: couple

        if (couple(0) == view_type) {
         // compteur de repétition consecutive
          count = count + 1
          //liste des zooms
          if (!(zoom.contains(couple(1))))
            zoom = couple(1) :: zoom

        } else {
         // ne pas imprimer la premiere ligne contenant la valeur d'intialisation
          if (view_type != "")
            //le résultat est lu sur la console
            println(view_type + " " + count + " " + zoom.mkString(", "))
            //réintialisation des variables
          zoom = List.empty[String]
          zoom = couple(1) :: zoom
          view_type = couple(0)
          count = 1
        }

        couple = List.empty[String]
      }
 } catch { case e: Exception => }
    }
    bufferedSource.close
   
  }
}