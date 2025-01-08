from diffusers import DiffusionPipeline
import torch
import os

if torch.cuda.is_available():
    print("Cuda est disponible !")
elif torch.backends.mps.is_available():
    print("MPS dispo")
    mps_device = torch.device("mps")
    x = torch.ones(1, device=mps_device)
    print(x)
else:
    print("Aucune n'est pas disponible !")


# Fonction pour générer plusieurs images à partir d'un prompt
def generate_images(prompt, negative_prompt="ugly, NSFW, scary, gross, weird, creepy, bad quality", 
    output_dir="BdD", image_size=(512, 512), num_images=4):
    
    new_prompt = f"Represent the concept of '{prompt}', it should be detailed"
    
    if not os.path.exists(output_dir):
        os.makedirs(output_dir)

    # Charger le pipeline Stable Diffusion
    pipeline = DiffusionPipeline.from_pretrained(
        "stabilityai/stable-diffusion-3.5-large-turbo"
    )
    pipeline = pipeline.to("mps" if torch.backends.mps.is_available() else "cuda" if torch.cuda.is_available() else "cpu")

    # Générer plusieurs images avec prompt négatif (si fourni)
    for i in range(num_images):
        image = pipeline(new_prompt, negative_prompt=negative_prompt, height=image_size[0], width=image_size[1]).images[0]
        
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