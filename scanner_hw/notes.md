# Ideation of design

## Card information

Card size:

- width(mm): 63
- height(mm): 88
- thickness(mm): .305

## General idea

- Utilize stacked rollers to remove cards from stack
  - Potentially softer handling of cards
  - No alignment or long range servo required
  - simple oring for card traction
    - need to sample and test different oring sizes
    - targeting ~20mm roller diameters?
      - verify with sample costs from online sources
  - Allows for 'wiper' component to be added to make sure cards are seperated
- Use roller to move card to `detection` area
  - additional roller can be used here to make sure card is in same general area
  - soft touch orings also good to use here to ensure no card damage
- sorting bin moves on linear rail to 'catch' cards as they are ejected from detection area
- generally move from stack -> detection area -> sorting bin
- **need to make quick sketch, possible 3d model**

## Possible parts design list

- Roller assemblies
  - roller
  - orings
  - fasteners?
- card stack holder
  - simple bin, max capacity relates to sorting bin capacities
- detection area
- sorting bin
  - various bins (max qty target?)
  - likely assume minimum ability to sort for...
    - WUBRG + multi color + colorless + lands
      - might just go to WUBRG only? then can 'resort' to further go down from there?
- sorting bin mechanism
  - linear rail?
  - belt driven?
