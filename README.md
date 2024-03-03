# Autor: Miguel Angel Morales García


## Arquitectura: MVVM con clean architecture
Se eligio la meta arquitectura de clean architecture para estructurar el proyecto en 3 capas:
    1.-Data
    2.-Domain
    3.Presentation

Ademas se utilizo MVVM para separar las clases en categorias como lo son Modelo Vista y conectar estas dos con ViewModel

## Librerias: 
Para este proyecto se usaron las siguientes librerias:
    1.-[Retrofit]: para las llamadas a API
    2.-[Dagger-Hilt]: para la inyeccion de dependencias
    3.-[Coil]: para cargar las imagenes via URL
    4.-[Maps]: para cargar las coordenadas de cada receta
    5.-[Jetpack Compose]: para toda la UI

## Buenas practicas:
Para este proyecto se utilizaron se patrones de diseño y algunos de los principios SOLID como:
    1.-[Single Responsibility]: aplicado en modulos y clases
    2.-[Open-Closed]: aplicado en todo el proyecto
    3.-[Liskov Substitution]: aplicado en todas las interfaces
    4.-[Interface Segregation]: aplicado en todas las interfaces
    5.-[Dependency Inversion]: aplicado en partes como el repository
Ademas se utilizaron patrones de diseño como repository, singleton, mapper entre otros y ventajas de kotlin como corutinas y null safety

## Estrategia:
Basicamente se opto por tener varios modulos para inyectar las principales dependencias del proyecto, estos modulos estan en la carpeta [di],
toda la logica de el comportamiento de las clases y como se transportan los datos esta en la carpeta [domain], todas las clases para modelar 
los datos estan en [data] y todo lo relacionado con la UI esta en [presentation] incluyendo los viewModels que se encargan de comunicar los
datos con la interfaz de usuario, ademas desde aqui se controlan los estados de la UI, es decir si tenemos el estado Success esto cambiara
la interfaz de usuario automaticamente.

## Test:
Para este proyecto se realizaron Unit Tests y End to End Test para probar
    1.-[UseCases]
    2.-[UI]
Sin embargo no se pudieron realizar las suficientes pruebas por cuestion de tiempo

