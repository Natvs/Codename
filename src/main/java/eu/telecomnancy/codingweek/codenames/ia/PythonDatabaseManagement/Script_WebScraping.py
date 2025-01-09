import os
import time
import base64
import requests
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.firefox.service import Service
from selenium.webdriver.firefox.options import Options
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


def rechercher_internet_champLexical(mot_cle, 
    url = "https://www.rimessolides.com/motscles.aspx", chemin_fichier_svg="Selec.txt"):

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


def save_base64_image(base64_str, file_path):
    """Enregistrer une image à partir d'une chaîne base64."""
    img_data = base64.b64decode(base64_str.split(',')[1])  # Enlever le préfixe data:image/*
    with open(file_path, 'wb') as file:
        file.write(img_data)

def save_image_from_url(url, file_path):
    """Télécharger une image à partir d'une URL."""
    try:
        response = requests.get(url, stream=True)
        if response.status_code == 200:
            with open(file_path, 'wb') as file:
                for chunk in response.iter_content(1024):
                    file.write(chunk)
    except Exception as e:
        print(f"Erreur lors du téléchargement depuis l'URL : {e}")

def rechercher_internet_image(word_search, nb_picture=5):
    assert os.path.exists("BdD")

    options = Options()
    options.add_argument("--disable-blink-features=AutomationControlled")
    options.set_preference("general.useragent.override", 
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36")
    service = Service()
    driver = webdriver.Firefox(service=service, options=options)
    driver.get("https://www.google.com/imghp")

    # Gestion du consentement
    try:
        time.sleep(1)
        WebDriverWait(driver, 10).until(
            EC.element_to_be_clickable((By.XPATH, '//*[@id="W0wltc"]'))
        ).click()
        time.sleep(2)
    except Exception as e:
        print("Consentement : 'Tout refuser' non trouvé :", e)

    # Recherche d'images
    search_box = driver.find_element(By.NAME, "q")
    search_box.send_keys(word_search)
    search_box.send_keys(Keys.RETURN)

    # Cibler les images dans la div spécifiée
    image_elements = WebDriverWait(driver, 20).until(
        EC.presence_of_all_elements_located((By.CSS_SELECTOR, "#search img.YQ4gaf"))
    )[:nb_picture * 2]

    os.makedirs(f"BdD/{word_search}", exist_ok=True)
    nb_picture_download = 0

    for i, img in enumerate(image_elements):
        if i%2 == 0:
            try:
                img_src = img.get_attribute("src")

                if img_src and img_src.startswith("data:image/"):
                    # Sauvegarder une image en base64
                    file_extension = img_src.split(';')[0].split('/')[1]  # Exemple : "data:image/png;base64,"
                    file_path = os.path.join("BdD", word_search, f"{word_search}_{nb_picture_download + 1}.{file_extension}")
                    save_base64_image(img_src, file_path)
                    nb_picture_download += 1
                elif img_src and (img_src.startswith("http://") or img_src.startswith("https://")):
                    # Télécharger une image via une URL
                    file_extension = img_src.split('.')[-1].split('?')[0]  # Obtenir l'extension du fichier
                    if file_extension.lower() in ['jpg', 'jpeg', 'png']:
                        file_path = os.path.join("BdD", word_search, f"{word_search}_{nb_picture_download + 1}.{file_extension}")
                        save_image_from_url(img_src, file_path)
                        nb_picture_download += 1

                if nb_picture_download >= nb_picture:
                    break

            except Exception as e:
                print(f"Erreur lors de l'extraction d'une image : {e}")

    if nb_picture_download < nb_picture:
        print(f"\nAttention : {nb_picture - nb_picture_download} image(s) manquante(s) pour {word_search}.")

    driver.quit()
