# Andarí – 19 pantallas: Dama, Caballero y Niño (Compose Multiplatform)

Proyecto completo, basado en la misma estructura y librerías de tu proyecto de clase
(`androidApp` + `shared`, PreCompose). Las pantallas se construyeron leyendo directamente tu Figma
(posiciones, tamaños, colores y fuentes exactas).

## Cómo probarlo
1. Descomprime el ZIP (carpeta `Andari`, sin acentos ni espacios en la ruta).
2. Android Studio -> **File -> Open** -> elige la carpeta `Andari` -> Trust Project.
3. Espera el **Gradle Sync** (la primera vez descarga cosas y tarda).
4. Arriba elige la configuración **androidApp** + tu emulador/celular y presiona **Run**.

## Flujo (copiado del prototipo de Figma)
- Inicio -> *Comenzar* -> Login -> *Entrar* -> Menú Dama -> tocar un huarache -> Artículo
- Inicio -> *Regístrate* -> Crear cuenta -> *Guardar* -> Menú Dama
- Login -> *¿Olviaste tú contraseña?* -> Recuperar contraseña -> *Cambiar contraseña* -> Login
- Casita (Login y Recuperar) -> Inicio. Flecha circular del Login -> Inicio
- Artículo: el ícono de usuario (arriba izquierda) regresa al Menú Dama
- Menú Dama / Caballero / Niño: las pestañas *Dama*, *Caballero* y *Niño* se mueven entre los 3 menús; cada uno abre sus 4 artículos
- En Caballero y Niño, la casita regresa al Menú Dama
- Artículo (de cualquier sección): el ícono de usuario regresa al menú del que vino

## Dónde está cada cosa (`shared/src/commonMain/`)
- `kotlin/com/example/andari/App.kt` ............ navegación
- `.../model/Producto.kt` ....................... datos de los 4 productos Dama
- `.../ui/theme/` ............................... colores y fuentes
- `.../ui/components/Lienzo.kt` ................. ayudantes para colocar cosas con coordenadas de Figma
- `.../ui/screens/` ............................. las pantallas
- `composeResources/drawable/` .................. imágenes (logo, huaraches, fondos, íconos)
- `composeResources/font/` ...................... Raleway, Inter, Nunito, Montserrat Subrayada, Poppins, Font Awesome

## Cómo ajustar algo
Cada elemento se coloca con los números de Figma (x, y, ancho, alto), por ejemplo:
`Texto("Talla", 34f, 448f, 24f, ...)` = x 34, y 448, tamaño 24. Cambia el número y vuelve a correr.

## Detalles del diseño que se respetaron tal cual (por si quieres corregirlos en Figma y en el código)
- "RECUPERER" (con E) y "¿Olviaste tú contraseña?" en el texto de esas pantallas
- "Artesania Mexicana", "Tacon", "comoda" sin acento
- Esqueleto lleva la misma descripción del Forro de Colores (dice "trensado colorido")
- Tacón Plataforma inicia con la talla 25 seleccionada; las otras con 24
- El 4.º producto de cada menú (Esqueleto / Sandalia con Bordado) no lleva tarjeta blanca, igual que en Figma
- En Figma, la tarjeta 3 del Menú Caballero tiene además un vínculo viejo al artículo Tacón Dama; se ignoró y abre Huarache de Cuero
- Las tallas de Caballero son 25, 26, 27 y 28 (inicia en 26) y las de Niño 10, 12, 14 y 16 (inicia en 12), como en tu diseño
- "Unisex Forro en Tonos Café" cuesta $490 en el menú Niño pero $450 en su artículo (así está en Figma)
- Las pantallas son 19: Inicio, Login, Recuperar, Crear cuenta + 3 menús + 12 artículos (4 por sección)
- En Figma hay un punto suelto (".") bajo el botón Comenzar; no se incluyó

## Notas técnicas
- Se escala al ancho del teléfono: el diseño se ve igual en cualquier pantalla.
- Si una pantalla es más alta que el teléfono, se puede desplazar hacia abajo.
- Si algo sale en rojo en Android Studio: **Build -> Rebuild Project**.
