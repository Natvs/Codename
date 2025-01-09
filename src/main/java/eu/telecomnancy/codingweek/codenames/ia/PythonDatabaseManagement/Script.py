import os
import shutil
from tqdm import tqdm
from Script_traitement import import_listeDeMot, traiter_champLexical
from Script_WebScraping import rechercher_internet_champLexical, rechercher_internet_image
from Script_nettoyer_BdD import nettoyer


"""
print("BdD: Compléter le fichier ChampLexical.txt")
liste_mots_dico = import_listeDeMot("Dico.txt")
for mot in tqdm(liste_mots_dico):
    rechercher_internet_champLexical(mot)
    traiter_champLexical()
"""

"""
print("BdD: Télécharger les images associer au dico.txt")
if os.path.exists("BdD"):
    shutil.rmtree("BdD")
os.makedirs("BdD")
liste_mots_dico = import_listeDeMot("Dico.txt")
for mot in tqdm(liste_mots_dico):
    rechercher_internet_image(mot, nb_picture=5)
"""


nettoyer()

