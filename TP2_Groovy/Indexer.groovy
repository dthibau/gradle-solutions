
def text='''Pour le deuxième hiver consécutif, Delhi étouffe sous la pollution. Dans la nuit du 6 au 7 novembre, alors que les températures chutaient à 
l’approche de l’hiver, quand le vent s’est arrêté de souffler, des milliards de milliards de particules fines ont été prises au piège dans l’atmosphère 
de la capitale indienne. En 2015, la pollution atmosphérique a entraîné 525 000 morts prématurées en Inde. En Chine, en décembre 2016, quelque 
460 millions de personnes ont été affectées par le smog de Pékin.
Les résultats d’une étude publiée en novembre 2016 sur le site de la revue
Proceedings of the National Academy of Sciences montrent que le smog en Chine et le 
brouillard de Londres qui, au cours de l’hiver 1952, tua quelque 12 000 personnes en cinq 
jours sont dus à des processus de réaction chimique similaires. Le responsable n’est autre que 
le dioxyde d’azote issu de la combustion du charbon. Mélangé au dioxyde de soufre, issu de la 
même combustion, il crée un acide sulfurique et un brouillard épais'''

// Utiliser une regexp pour construire un tableau de mots
def words = text.split(/\b/)

// Définition d'une map. Clé mot / Valeur nb d'occurences
def map = [:]

// Définir une closure fillMap qui ajoute +1 dans la map
def fillMap = {
	int occurence = map.get(it.toLowerCase(),0);
	map[it.toLowerCase()]=occurence+1;
}

// Appel de fillMap pour chaque mot
words.each(fillMap)

 println 'Step 1 ' + map

// 2ème partie : on ne prend en compte que les mots de plus de 2 caractères
map = [:]

// Appel de fillMap pour chaque mot de plus de 2 caractères
words.findAll {it.size()>2} each(fillMap)

println 'Step 2 ' + map

// Tri par mot 

println 'Step 3 ' + map

// InvertedMap
def invertedMap = new TreeMap();
for (key in map.keySet()) {
	int value = map[key];
	mots = invertedMap.get(value,[]);
	mots += key
	invertedMap[value] = mots
}

println 'Step 4 ' + invertedMap

// Boucle sur une Range
println 'Top parade :'
(3..1).each({println invertedMap[it]})


