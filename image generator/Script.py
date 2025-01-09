from diffusers import StableDiffusion3Pipeline
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
    
# Charger le pipeline Stable Diffusion
pipeline = StableDiffusion3Pipeline.from_pretrained(
    "tensorart/stable-diffusion-3.5-medium-turbo", torch_dtype=torch.float16)
pipeline = pipeline.to("mps" if torch.backends.mps.is_available() else "cuda" if torch.cuda.is_available() else "cpu")


# Fonction pour générer plusieurs images à partir d'un prompt
def generate_images(prompt,prompt_fr, negative_prompt="ugly, bad quality, text", 
    output_dir="BdD", image_size=(512, 512), num_images=3):
    
    new_prompt = f"Example of '{prompt}', it can be a drawing, a logo, a photography, a representation, it should be understood by anyone"
    
    if not os.path.exists(output_dir):
        os.makedirs(output_dir)

    # Générer plusieurs images avec prompt négatif (si fourni)
    for i in range(num_images):
        image = pipeline(new_prompt, 
                         negative_prompt=negative_prompt, 
                         height=image_size[0], 
                         width=image_size[1],
                         num_inference_steps=12,
                         guidance_scale=1.0).images[0]
        # Sauvegarder chaque image avec un nom unique
        output_path = os.path.join(output_dir, f"{prompt_fr.replace(' ', '_')}_{i+1}.png")
        image.save(output_path)

# Lecture des prompts depuis un fichier
word_list = []
with open("dico.txt", 'r') as fichier:
    for ligne in fichier.readlines():
        word = ligne.strip()
        word_list.append(word)
        
word_list_fr = []        
with open("codenames_words.txt", 'r') as fichier:
    for ligne in fichier.readlines():
        word_fr = ligne.strip()
        word_list_fr.append(word_fr)

# Générer des images pour chaque prompt
for i in range(len(word_list)):
    generate_images(word_list[i], word_list_fr[i])