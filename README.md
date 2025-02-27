# ProjetASD3
réalisé par Louis-Marie Boyenval & Eliot Belouin 584I


Les noms des fichiers args.txt, test1.txt à test5.txt, sont des tests pour les quadTree. Les fichiers de test6.txt à test7.txt concerne les arbres ternaires

## Arbre Quaternaire

### Stratégie
```
 _______
|no |ne |
|_ _|___|
|so |se |
|___|___|
```

Quand un point est placé, il découpe notre arbre quaternaire en 4 sous région:
- no : en haut à gauche
- ne : en haut à droite
- so : en bas à gauche,
- se : en bas à droite


### Format du fichier d'entrée

Le format du fichier d'entrée doit être de la forme:
```
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
```


## Arbre Ternaire

Compilation:
```javac -source 1.7 -target 1.7 -d boyenvalbelouin/bin boyenvalbelouin/src/*.java```

Execution:
```java -classpath boyenvalbelouin/bin MonBoTablo 2 boyenvalbelouin/test1.txt boyenvalbelouin/Sortie/```


**MonBoTablo :** Nom du programme principale
**2 :** Numéro de stratégie
**boyenvalbelouin/test1.txt :** Nom du fichier d'entrée
**boyenvalbelouin/Sortie/ :** Nom du dossier de sortie

Quand un point est placé, il découpe notre abre ternaire en 3 sous région:
- O : Il représente toute la région de gauche
- ne : en haut à droite
- se : en bas à droite
 ```
 _______
|   |ne |
| O |___|
|   |se |
|___|___|
```
### Format du fichier d'entrée

Ce qui change est le nombre de couleur après les points. Comme c'est un arbre ternaire, il n'y en a que 3
1000
5
600, 500, R, G, J
900, 400, J, B, N
800, 300, G, R, B
850, 350, R, J, R
540, 120, B, R, J
