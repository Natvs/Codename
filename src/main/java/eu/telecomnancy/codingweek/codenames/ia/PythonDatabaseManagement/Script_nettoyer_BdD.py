import os
import shutil
import math
import tkinter as tk
from tkinter import messagebox
from PIL import Image, ImageTk


# Charger les images d'un répertoire donné
def charger_images(repertoire="BdD"):
    liste_paquets_images = []

    for file_name in os.listdir(repertoire):
        file_path = os.path.join(repertoire, file_name)
        liste_images = []
        for image_name in os.listdir(file_path):
            if image_name.lower().endswith(('.png', '.jpg', '.jpeg', '.gif', '.bmp')):
                image_path = os.path.join(file_path, image_name)
                image = Image.open(image_path)
                liste_images.append((image, image_path))  # Inclure le chemin complet
        liste_paquets_images.append(liste_images)
    return liste_paquets_images

# Afficher les images 5 par 5 dans une fenêtre Tkinter
def afficher_images(liste_images, fenetre):

    nb_images = len(liste_images)

    # Définir la taille des images à l'affichage
    image_width = 200
    image_height = 200

    # Définir les dimensions de la grille
    grid_dim = math.ceil(math.sqrt(nb_images))

    # Définir la taille de la fenêtre en fonction du nombre d'images
    canvas_width = grid_dim * image_width
    canvas_height = grid_dim * image_height
    fenetre.geometry(f"{canvas_width}x{canvas_height}")

    images_affichees = []  # Liste pour garder les références aux images
    boutons = []  # Liste pour garder les boutons

    for i in range(grid_dim):
        for j in range(grid_dim):
            index = i * grid_dim + j
            if index < nb_images:
                img, path = liste_images[index]
                img = img.resize((image_width, image_height))  # Redimensionner pour les afficher proprement
                img_tk = ImageTk.PhotoImage(img)  # Convertir en format Tkinter
                images_affichees.append(img_tk)  # Garder une référence à l'image
                bouton = tk.Button(fenetre, image=img_tk, 
                    command=lambda idx=index: image_selectionnee(idx, liste_images, fenetre))
                bouton.grid(row=i, column=j)
                boutons.append(bouton)  # Garder une référence au bouton
            else:
                break

    return images_affichees, boutons

# Fonction appelée lorsqu'une image est sélectionnée
def image_selectionnee(index, liste_images, fenetre):
    # Récupérer le chemin de l'image sélectionnée
    _, selected_path = liste_images[index]

    # Supprimer toutes les autres images
    for _, path in liste_images:
        if path != selected_path:
            os.remove(path)  # Supprimer l'image non sélectionnée

    # Fermer la fenêtre
    fenetre.destroy()

def couper_coller_image(source_path, destination_path):
    try:
        shutil.move(source_path, destination_path)
    except Exception as e:
        print(f"Erreur lors du déplacement : {e}")


# Fonction principale
def nettoyer():
    assert os.path.exists("BdD")

    liste_paquets_images = charger_images()

    for liste_images in liste_paquets_images:
        # Créer la fenêtre Tkinter
        fenetre = tk.Tk()
        fenetre.title("Sélection d'images")

        images_affichees, boutons = afficher_images(liste_images, fenetre)

        # Lancer la boucle principale Tkinter
        fenetre.mainloop()

    for file_name in os.listdir("BdD"):
        file_path = os.path.join("BdD", file_name)
        image_name = os.listdir(file_path)[0]
        image_path = os.path.join(file_path, image_name)
        image_name_destination = image_name.split(sep='_')[0] + '.' + image_name.split(sep='.')[1]
        image_path_destination = os.path.join("BdD", image_name_destination)
        couper_coller_image(image_path, image_path_destination)
        shutil.rmtree(file_path)
