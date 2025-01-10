# CodingWeek 2025
**TELECOM Nancy - 06/01 au 10/01**

## Pyinstaller Configuration:
pyinstaller Script_DictionaryManager.py --onefile --windowed
dist/Script_DictionaryManager.exe "abat-jour" "BdD" "codenames_words.txt" "lexical_field.txt"


## Liste de tâches :

### Arthur JEANNE :
- [X] 06/01: Implémenter la liste des tâche à venir pour chacun des membres du groupe
- [X] 06/01-07/01: Implémentation de la structure du modèle selon le diagramme de class UML
                - Implémentation de la structure "Clue"
                - Implémentation de la structure "Player"
                - Implémentation de la structure "Team"
                - Implémentation de la structure "AgentTeam"
                - Implémentation de la structure "SpyTeam"
                - Implémentation de la structure "Color"
                - Implémentation de la structure "CardState"
                - Implémentation de la structure "Card"
                - Implémentation de la structure "Board"
                - Implémentation de la structure "GameConfig"
                - Implémentation de la structure "Session"
- [X] 08/01: Création du dictionnaire de 1000 mots
- [X] 08/01-09/01: Création de Script python pour la géstion de la Base de Donnée (BdD):
                - Création d'un script python pour récupérer sur internet les champs léxicaus du dictionnaire 
                        (Pour la création de thème et la BdD de l'IA)
                - Création d'un script python pour télécharger les images sur internet
                - Création d'un script python pour séléctionner et traiter les images téléchargé
- [X] 09/01: Création des Base de Donée
- [X] 09/01: Implémentation de la structure "Thème" pour la création automatique de thème
                - Implémentation de la structure "Build"
                - Implémentation de la structure "Utility"
- [X] 09/01: Implémentation de la class "Search" dans la structure Thème pour la recherche de mot
- [X] 09/09: Création de testes pour la structure "Thème"

### Luca Mandrelli :
- [X] 06/01: Implémentation de la fenètre "Acceuil" :
        - Ajouter un bouton "Nouvelle Partie"
        - Ajouter un bouton "Quitter"
- [X] 06/01: Implémentation de la fenètre "Menu Configuration" :
        - Ajouter un bouton statique "Joueur" (Pour fixer le nombre de joueur)
        - Ajouter un bouton statique "Grille" (pour fixer la taille de la grille)
        - Ajouter un bouton statique "Thématique" (pour choisir un thème de mot)
        - Ajouter un bouton "Acceuil"
- [X] 06/01-08/01: Implémentation de la fenètre "Partie (Maitre-Espion)" :
        - Implémenter l'affichage de la grille
        - Afficher les cartes couleur déjà positionnée sur la grille
        - Ajouter un bouton "Sauvegarder et Quitter"
        - Ajouter un bouton "Passe" (pour terminer le tour et transissionner)
        - Implémenter l'affichage de la carte clée dans la grille pour l'espion
- [X] 08/01 : Changement du "Menu Configuration" : 
        - Ajout d'une gestion de la répartition de l'équipe
        - Validation du formulaire
        - Relié au backend
        - Les champs textes peuvent etre ajoutés et supprimés dynamiquement
- [X] 08/01 : Generation d'image pour chaque mots :
        - Config de stable diffusion, premiers tests de prompts
        - Faire tourner stable diffusion sur le mac, verifier les resultats
- [X]  09/01 : Fix de la grille de jeu : 
        - La config de la grille n'est pas repectée
- [ ]  09/01 : Gestion de la sauvegarde sur le front :
        - Ajouter les boutons de sauvegarde et de chargement de sauvegarde
        - Gerer le path de sauvegarde et load par boite de dialogue
- [ ]  09/01 : CSS -> rework du design pour que ce soit ✨ visuellement potable ✨
- [ ]  Ajout des themes dans le menu de configuration :
        - Propose des mots au fil des entrées clavier (si c'est possible sur JavaFX proprement)
        - Recuperer la liste de mots
        - La donner au generateur de theme d'Arthur
        - Remplacer le dictionnaire de jeu par celui des champs lexicaux choisis.

### Lucas Simonneau :
- [X] 06/01: Implémentation de gestion de fichier :
        - Implémentation de la structure de donnée ouverture et modification d'un fichier texte
        - Réalisation de tests
- [X] 06/01-07/01: Implémentation de la constitution du Dictionnaire :
        - Ouvrir un fichier texte : une BdD de mots issue du dictionnaire français
        - Selection d'un nombre fini de mots aléatoire
        - Asignement d'une couleur pour chaque mot
        - Convertion sous forme de grille
        - Réalisation de tests
- [X] 07/01 - 10/01: Implémentation de la musique au jeu
        - Lecture d'une musique et changement de musique
        - Mettre le son ou le couper 
- [X] 07/01: Implémentation de la sauvegarde (Back-End)
        - Chargement d'un fichier
        - Sauvegarde d'une partie sous forme d'un fichier .json
        - Suppression d'une partie
        - Réalisation de tests
- [X] 08/01: Implémentation de la fenêtre "Partie" :
        - Afficher le mot indice pour l'agent
        - Ajouter un bouton "Mot" pour l'agent
        - Afficher le nombre de mot à deviner par l'Agent
        - Ajouter un bouton "Nombre" pour l'espion
- [X] 09/01: Implémentation du Timer :
        - Affichage du timer
        - Changement de joueur si le délai est atteint
        - Gestion du thread de timer 
        - Actualisation graphique en temps réel du timer
- [X] 10/01: Implémentation des crédits :
        - Rédaction de la partie Back-End des crédits
- [X] 10/01: Implémentation de l'affichage de l'ajout d'un mot :
        - On peut ajouter un mot. Le clic sur le bouton submit envoie vers le Back-End.

### Nathans Vernois :
- [X] 06/01: Implémentation du diagramme de classe
- [X] 07/01: Implémentation de racourcis claviers (commencer une partie, quitter)
- [X] 07/01: Ajout des patters observers et commands
- [X] 08/01: Implémentation de la fenètre "Partie" :
        - Pouvoir selectionner la carte pour l'espion
	- Ajout d'un observateur
- [X] 08/01 Implémentation de la gestion des tours:
	- Ajout dans le modèle
	- Affichage du joueur actuel
	- Gestion de la selection de la carte selon le role du joueur
- [X] 09/01: Implémentation de la fenètre "Transition" :
        - Afficher l'Equipe (Bleu ou Rouge) et le Groupe (Espion ou Maitre-Espion)
        - Ajouter un bouton "Jouer"
- [X]  09/01 : Fin de jeu :
        - Quand toutes les cartes d'une couleur sont revélées
        - Carte noire selectionnée
- [X] 10/01 : Gestion du jeu 
        - Ajout de nombreux observers afin de lier le modèle aux graphismes
        - Ajout de commandes afin de simplifier la gestion du jeu
- [X] 10/01 : Implémentations de plusieurs petits aspects du jeu :
        - Ajout de raccourcis claviers pour toutes les pages (retour, charger une partie, sauvegarder, passer)
        - Le timer disparait lorsqu'il n'a pas été activé dans les configurations
        - Les mots des indices doivent appartenir au dictionnaire pour être validés
- [X] 10/01 : Ajout des boutons pour inclure des IA en tant que espions