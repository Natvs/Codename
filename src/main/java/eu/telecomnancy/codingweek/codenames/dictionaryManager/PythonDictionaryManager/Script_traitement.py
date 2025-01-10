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

def reorganiser_ordreFrequenceMot(liste_mots_selec):
    liste_mots_selec_reorganise = []
    for k in range(4, 0, -1):
        for i in range(len(liste_mots_selec)):
            if (i+k)%4 == 0:
                liste_mots_selec_reorganise.append(liste_mots_selec[i])
    return liste_mots_selec_reorganise


def traiter_champLexical(codenamesWords_path):
    liste_mots_dico = import_listeDeMot(codenamesWords_path)
    liste_mots_selec = supprimer_virgule(import_listeDeMot("Selec.txt"))
    # liste_mots_selec_reorganise = reorganiser_ordreFrequenceMot(liste_mots_selec)
    liste_intersection = intersection(liste_mots_selec, liste_mots_dico)
    return liste_intersection

def obtenir_listeIdMot(liste_champLexical, codenamesWords_path):
    liste_mots_dico = import_listeDeMot(codenamesWords_path)

    liste_idMot = []
    for mot in liste_champLexical:
        Id = liste_mots_dico.index(mot) +1
        liste_idMot.append(Id)
    return liste_idMot

def obtenir_nouvelIdMot(mot, codenamesWords_path):
    liste_mots_dico = import_listeDeMot(codenamesWords_path)
    liste_mots_dico.append(mot)
    trier_liste(liste_mots_dico)
    nouvel_id_mot = liste_mots_dico.index(mot) +1
    return nouvel_id_mot


def ajouter_champLexical_fichier(mot, codenamesWords_path, lexicalField_path):
    liste_champLexical = traiter_champLexical(codenamesWords_path)
    liste_idMot_champLexical = obtenir_listeIdMot(liste_champLexical, codenamesWords_path)

    nouvel_id_mot = obtenir_nouvelIdMot(mot, codenamesWords_path) 
    nouvelle_liste_mot = []
    id_mot = 1
    fichier = open(codenamesWords_path, 'r')
    for ligne in fichier.readlines():
        if id_mot == nouvel_id_mot:
            nouvelle_liste_mot.append(f"{mot}\n")
        nouvelle_liste_mot.append(ligne)
        id_mot += 1
    fichier.close()

    fichier = open(codenamesWords_path, 'w')
    texte = "".join(nouvelle_liste_mot)
    fichier.write(texte)
    fichier.close()
    
    nouvelle_liste_champLexical = []
    id_mot = 1
    fichier = open(lexicalField_path, 'r')
    for ligne in fichier.readlines():
        if id_mot == nouvel_id_mot:
            nouvelle_liste_champLexical.append(str(liste_idMot_champLexical)[1:-1] + "\n")
        nouvelle_liste_champLexical.append(ligne)
        id_mot += 1
    fichier.close()

    fichier = open(lexicalField_path, 'w')
    texte = "".join(nouvelle_liste_champLexical)
    fichier.write(texte)
    fichier.close()
