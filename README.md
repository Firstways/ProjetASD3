# ProjetASD3

## Format du fichier d'entree

Le format du fichier d'entrée doit être de la forme:
1000
5
600, 500, R, G, J, B
900, 400, J, B, N, B
800, 300, G, R, B, J
850, 350, R, J, R, J
540, 120, B, R, J, G
19
4
400, 300, G
570, 250, G
700, 10, R
580, 12, G
// ligne 1: pour l’image initiale, le côté n (en nombre de pixels). Ici n = 1000.
// ligne 2: le nombre m ≥ 0 de centres fournis. Ici m = 5.
// ligne 3: le 1er centre.
// ligne 4: le 2ème centre.
// etc.
// ligne m + 2: le m-ème centre.
// ligne m + 3: l’épaisseur e ≥ 1 (valeur impaire) du trait. Ici e = 19.
// ligne m + 4: nombre k ≥ 0 de paires de recoloriage fournies ci-dessous, pour recoloriages successifs.
// ligne m + 5: première paire, sous la forme x, y, couleur
// ligne m + 6: deuxième paire.
// etc.
// ligne m + 4 + k: dernière paire.

Si le format n'est pas respecté, une erreur est levé
