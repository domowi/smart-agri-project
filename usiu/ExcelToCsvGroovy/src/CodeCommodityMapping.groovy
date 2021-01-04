// Map<String, Integer> codeMap = new HashMap<>()
def codeMap = [
        1 : "Avocado", 2 : "Cooking Bananas", 3 : "Ripe Bananas", 4 : "Beans Canadian", 5 : "Beans Rosecoco", 6 : "Mwezi Moja", 7 : "Beans Mwitemania", 8 : "Brinjals", 9 : "Cabbages", 10 : "Cauliflower",
        11 : "Chillies", 12 : "Capsicums", 13 : "Carrots", 14 : "Cucumber", 15 : "Kales", 16 : "Lemons", 17 : "Lettuce", 18 : "Limes", 19 : "Green Maize", 20 : "Dry Maize",
        21 : "Mangoes Local", 22 : "Onions Dry", 23 : "Spring Onions", 24 : "Oranges", 25 : "Passion Fruits", 26 : "Pawpaw", 27 : "Fresh Peas", 28 : "Pineapples", 29 : "Red Irish Potatoes", 30 : "White Irish Potatoes",
        31 : "Sweet Potatoes", 32 : "Tomatoes", 33 : "", 34 : "", 35 : "Dolichos (Njahi)", 36 : "Mangoes Ngowe", 37 : "", 38 : "", 39 : "", 40 : "",
        41 : "Finger Millet", 42 : "Sorghum", 43 : "Cassava Fresh", 44 : "Groundnuts", 45 : "Cowpeas", 46 : "Green Gram", 47 : "Eggs", 48 : "Wheat"
              ]

String sorghumVal = "Sorghum"
String passionFruitsVal = "Passion Fruits"
String dolichosVal = " Dolichos (Njahi)"

codeMap.each { entry ->
    if(entry.value.trim() == sorghumVal.trim()){
        println "Sorghum Key: " + entry.key
    }

    if(entry.value.trim() == passionFruitsVal.trim()){
        println "Passion Fruit Key: " + entry.key
    }

    if(entry.value.trim() == dolichosVal.trim()){
        println "Dolichos (Njahi) Key: " + entry.key
    }

}