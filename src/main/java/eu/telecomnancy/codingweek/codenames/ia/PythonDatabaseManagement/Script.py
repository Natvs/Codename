from tqdm import tqdm
from Script_traitement import import_listeDeMot, traiter_champLexical
from Script_WebScraping import rechercher_internet


liste_mots_dico = import_listeDeMot("Dico.txt")
for mot in tqdm(liste_mots_dico):
    rechercher_internet(mot)
    traiter_champLexical()
