
L = [588, 484, 455, 818, 403, 415, 608, 628, 732, 680, 124, 37, 635, 812, 38, 114, 28,
        704, 755, 247, 652, 180, 636, 760, 424, 791, 803, 221, 504, 416, 837, 25, 588, 65, 417, 575, 670, 908, 434, 894]
L.remove(588)
L.sort()
print(L, len(L))


def import_listeDeMot(path="/home/arthur/Documents/JavaFX/CodingWeek/grp13/src/main/resources/words/codenames_words.txt"):
    fichier = open(path, 'r', encoding="utf-8")
    liste_mots = []
    for ligne in fichier.readlines():
        mot = ligne[:-1]
        liste_mots.append(mot)
    fichier.close()
    return liste_mots

theme = []
dico = import_listeDeMot()
for n in L:
    theme.append(dico[n-1])
print(theme)
