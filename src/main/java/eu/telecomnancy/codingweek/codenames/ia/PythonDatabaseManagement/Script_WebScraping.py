from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import time


def rechercher_internet(mot_cle, url = "https://www.rimessolides.com/motscles.aspx", chemin_fichier_svg="Selec.txt"):

    # Initialiser le navigateur Firefox
    options = webdriver.FirefoxOptions()
    options.add_argument("--headless")  # Optionnel : pour un mode sans interface graphique
    driver = webdriver.Firefox(options=options)

    try:
        # Ouvrir la page web
        driver.get(url)
        time.sleep(3)  # Attendre que la page se charge

        # Trouver la barre de recherche (ajustez le sélecteur CSS selon la page)
        search_box = driver.find_element("css selector", "input[type='text']")  # Changez le sélecteur si nécessaire
        search_box.send_keys(mot_cle)
        search_box.send_keys(Keys.RETURN)
        time.sleep(3)  # Attendre les résultats

        # Sélectionner tout le contenu de la page
        body = driver.find_element("tag name", "body")
        body.send_keys(Keys.CONTROL, 'a')  # CTRL + A
        body.send_keys(Keys.CONTROL, 'c')  # CTRL + C

        # Récupérer le contenu copié et l'enregistrer dans un fichier texte
        with open(chemin_fichier_svg, "w", encoding="utf-8") as fichier:
            texte = driver.find_element("tag name", "body").text
            texte_recadre = texte[102+len(mot_cle) : -201] + ",\n"
            fichier.write(texte_recadre)

    finally:
        # Fermer le navigateur
        driver.quit()


rechercher_internet("abeille")
