# CodingWeek 2025
**TELECOM Nancy - 06/01 au 10/01**

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
- [X] 08/01: Création du dictionnaire des mot française
- [X] 08/01: Création de la Base de Donnée pour l'IA:
		- Création d'un script python pour récupérer la liste des champs lexical associer à un mot sur internet
		- Création de scripts python pour générer la liste des champs lexical associer à chaque mot du dictionnaire

### Luca Mandrelli :
- [x] 06/01: Implémentation de la fenètre "Acceuil" :
        - Ajouter un bouton "Nouvelle Partie"
        - Ajouter un bouton "Quitter"
- [x] 06/01: Implémentation de la fenètre "Menu Configuration" :
        - Ajouter un bouton statique "Joueur" (Pour fixer le nombre de joueur)
        - Ajouter un bouton statique "Grille" (pour fixer la taille de la grille)
        - Ajouter un bouton statique "Thématique" (pour choisir un thème de mot)
        - Ajouter un bouton "Acceuil"
- [ ] 06/01: Implémentation de la fenètre "Transition" :
        - Afficher l'Equipe (Bleu ou Rouge) et le Groupe (Espion ou Maitre-Espion)
        - Ajouter un bouton "Jouer"
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

### Lucas Simonneau :
- [X] 06/01: Implémentation de gestion de fichier :
        - Implémentation de la structure de donnée ouverture et modification d'un fichier texte
- [X] 06/01-07/01: Implémentation de la constitution du Dictionnaire :
        - Ouvrir un fichier texte : une BdD de mots issue du dictionnaire français
        - Selection d'un nombre fini de mots aléatoire
        - Asignement d'une couleur pour chaque mot
        - Convertion sous forme de grille
- [X] 07/01: Implémentation de la musique au jeu
- [X] 08/01: Implémentation de la fenètre "Partie" :
        - Afficher le mot indice pour l'agent
        - Ajouter un bouton "Mot" pour l'agent
        - Afficher le nombre de mot à deviner par l'Agent
        - Ajouter un bouton "Nombre" pour l'espion

### Nathans Vernois :
- [X] 06/01: Implémentation du diagramme de classe
- [X] 07/01: Implémentation des racourcis claviers
        - Implémentation du racourcis "Quitter"
        - Implémentation du racourcis "Nouvelle Partie" (Acceuil)
        - Implémentation du racourcis "Démarer Partie" (Menu configuration)
- [X] 08/01: Implémentation de la fenètre "Partie" :
        - Pouvoir selectionner la carte pour l'espion
	- Ajout d'un observateur
- [X] 08/01 Implémentation de la gestion des tours:
	- Ajout dans le model
	- Affichage du joueur actuel
	- Gestion de la selection de la carte selon le role du joueur
