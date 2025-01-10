from Script_traitement import ajouter_champLexical_fichier
from Script_WebScraping import rechercher_internet_champLexical, rechercher_internet_image
    

def manage(word, image_save_path="BdD", codenamesWords_path="codenames_words.txt", lexicalField_path="lexical_field.txt"):
    
    # Compléter le fichier ChampLexical.txt
    rechercher_internet_champLexical(word)
    ajouter_champLexical_fichier(word, codenamesWords_path, lexicalField_path)

    # Télécharger l'images associer au mot "word"
    est_telecharge = False
    while not est_telecharge:
        try:
            rechercher_internet_image(word, image_save_path)
            est_telecharge = True
        except Exception:
            est_telecharge = False


manage("abat-jour")
