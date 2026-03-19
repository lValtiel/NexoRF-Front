# 🖥️ Picking System - Frontend

Frontend del sistema de gestión de picking desarrollado en JavaFX.  
Proporciona una interfaz gráfica interactiva para gestionar productos, órdenes y ejecutar el flujo de picking conectado al backend.

---

## 🚀 Tecnologías utilizadas

- Java 21  
- JavaFX  
- Arquitectura MVC  
- HTTP Client (consumo de API REST)  
- CSS (estilos en JavaFX)  

---

## 📚 Descripción

Este frontend permite interactuar con el sistema de picking de forma visual e intuitiva.

Incluye:

- Gestión de inventario  
- Visualización de órdenes  
- Flujo de picking paso a paso  
- Actualización en tiempo real de datos  
- Navegación dinámica entre vistas  

El sistema se conecta a un backend desarrollado en Spring Boot mediante API REST.

---

## 🧱 Arquitectura

El proyecto sigue una arquitectura basada en MVC:

- **Model** → DTOs y manejo de datos  
- **View** → Interfaces en JavaFX  
- **Controller** → Lógica de interacción y navegación  

Además, incluye:

- `NavigationController` → Control central de navegación  
- Vistas desacopladas y reutilizables  
- Separación clara entre lógica y UI  

---

## 🔄 Flujo de la aplicación

1. Usuario inicia sesión  
2. Accede al sistema principal  
3. Puede:
   - Gestionar productos  
   - Visualizar órdenes  
   - Ejecutar picking  
4. Durante el picking:
   - Se validan ubicaciones  
   - Se validan cantidades  
   - Se actualiza inventario  
   - Se cambia el estado de la orden automáticamente a **Completado**  

---

## 🔗 Conexión con el backend

Este frontend consume un backend REST.

Asegúrate de que el backend esté corriendo antes de iniciar la aplicación.

Las peticiones incluyen autenticación mediante JWT.

---

## ▶️ Ejecución del proyecto

### 1. Clonar repositorio

```bash
git clone https://github.com/lValtiel/NexoRF-Front.git
```

---

### 2. Configurar JavaFX

Asegúrate de tener configurado JavaFX en tu entorno:

- Añadir librerías de JavaFX  
- Configurar VM options si usas IntelliJ:

```bash
--module-path /ruta/javafx/lib --add-modules javafx.controls,javafx.fxml
```

---

### 3. Ejecutar la aplicación

Desde tu IDE:

- Ejecutar la clase principal del proyecto  

---

## 🧠 Funcionalidades destacadas

- Navegación dinámica entre vistas sin recargar la aplicación  
- Actualización de tablas en tiempo real  
- Flujo de picking guiado paso a paso  
- Validación de datos en UI (ubicación y cantidad)  
- Comunicación asíncrona con el backend  

---

## ⚠️ Notas importantes

- El backend debe estar en ejecución para que el sistema funcione correctamente  
- El token JWT se utiliza para autenticar las peticiones  
- El sistema está diseñado para uso en entornos de logística o almacenes  

---

## 🧑‍💻 Autor

Desarrollado por lValtiel.  
Sistema completo de picking con arquitectura separada (backend + frontend).
