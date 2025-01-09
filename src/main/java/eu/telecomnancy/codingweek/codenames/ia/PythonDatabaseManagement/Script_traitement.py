import unidecode


def import_listeDeMot(path):
    fichier = open(path, 'r', encoding="utf-8")
    liste_mots = []
    for ligne in fichier.readlines():
        mot = ligne[:-1]
        liste_mots.append(mot)
    fichier.close()
    return liste_mots

def intersection(liste1, liste2):
    liste_intersection = []
    if len(liste1) < len(liste2):
        for element in liste1:
            if element in liste2:
                liste_intersection.append(element)
    else:
        for element in liste2:
            if element in liste1:
                liste_intersection.append(element)
    return liste_intersection

def trier_liste(liste_mot):
    def f(mot):
        mot_capitalise = mot.upper()
        mot_capitalise_sansAccent = unidecode.unidecode(mot_capitalise)
        return mot_capitalise_sansAccent
    liste_mot.sort(key=f)

def supprimer_virgule(liste_mot):
    liste_mot_epure = []
    for mot in liste_mot:
        mot_sansVirgule = mot[:-1]
        liste_mot_epure.append(mot_sansVirgule)
    return liste_mot_epure


def traitement_champLexical():
    liste_mots_dico = import_listeDeMot("Dico.txt")
    liste_mots_selec = supprimer_virgule(import_listeDeMot("Selec.txt"))
    liste_intersection = intersection(liste_mots_selec, liste_mots_dico)
    return liste_intersection

def obtenir_listeIdMot(liste_champLexical):
    liste_mots_dico = import_listeDeMot("Dico.txt")

    liste_idMot = []
    for mot in liste_champLexical:
        Id = liste_mots_dico.index(mot) +1
        liste_idMot.append(Id)
    return liste_idMot


def traiter_champLexical(path="ChampLexical.txt"):
    liste_champLexical = traitement_champLexical()
    liste_idMot = obtenir_listeIdMot(liste_champLexical)

    fichier = open(path, 'a')
    ligne = str(liste_idMot)[1:-1] + "\n"
    fichier.write(ligne)
