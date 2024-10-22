<img width="1502" alt="Screenshot 2024-10-22 at 6 30 41 PM" src="https://github.com/user-attachments/assets/15c4e362-ca94-4883-9a98-c0bb7ff5bf32">
Amazon Price Drop Tracker
The Amazon Price Drop Tracker is a web application designed to help users monitor product prices on Amazon. Users can input a product URL along with their target price, and the application attempts to fetch both the product title and the current price. If the current price drops below the user-defined target, the app aims to notify the user.

Currently, the application is partially functional. It successfully fetches the product title, but retrieving the current price remains a challenge, and it’s showing as "0." This issue is under investigation, and we are actively working towards resolving it to ensure the core functionality works as intended.

The backend is built using Java with the Spring Boot framework, and Selenium is employed for scraping the required data from Amazon product pages. However, due to the dynamic nature of modern websites and the evolving structure of Amazon pages, ensuring consistent data retrieval is complex.

This project is a work in progress. Future improvements will include refining the scraping logic to address the current issues, adding background price checks every 60 minutes, and eventually implementing notification systems. For now, the focus is on stabilizing the data retrieval and ensuring accurate price tracking.
