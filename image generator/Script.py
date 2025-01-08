from diffusers import StableDiffusionPipeline
import torch
import os

if torch.cuda.is_available():
    print("Cuda est disponible !")
else:
    print("Cuda n'est pas disponible !")


# Fonction pour générer plusieurs images à partir d'un prompt
def generate_images(prompt, negative_prompt="bruit, fond flou, mauvaise qualité, moche", 
    output_dir="BdD", image_size=(512, 512), num_images=4):
    
    if not os.path.exists(output_dir):
        os.makedirs(output_dir)

    # Charger le pipeline Stable Diffusion
    pipeline = StableDiffusionPipeline.from_pretrained("CompVis/stable-diffusion-v1-4")
    pipeline = pipeline.to("cuda" if torch.cuda.is_available() else "cpu")

    # Générer plusieurs images avec prompt négatif (si fourni)
    for i in range(num_images):
        image = pipeline(prompt, negative_prompt=negative_prompt, height=image_size[0], width=image_size[1]).images[0]
        
        # Sauvegarder chaque image avec un nom unique
        output_path = os.path.join(output_dir, f"{prompt.replace(' ', '_')}_{i+1}.png")
        image.save(output_path)
        print(f"Image {i+1} pour le mot '{prompt}'")

# Lecture des prompts depuis un fichier
word_list = []
with open("dico.txt", 'r') as fichier:
    for ligne in fichier.readlines():
        word = ligne.strip()
        word_list.append(word)

# Générer des images pour chaque prompt
for prompt in word_list:
    generate_images(prompt)
