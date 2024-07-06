<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Room Gallery Slideshow</title>
        <style>
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }

            body {
                font-family: Arial, sans-serif;
                position: relative;
                padding: 20px;
                overflow-x: hidden;
            }
            .back-button {
                text-align: center;
                margin-top: 20px;
            }

            .back-button a {
                display: inline-block;
                background-color: #007bff; 
                color: #fff;
                padding: 10px 20px;
                text-decoration: none;
                border-radius: 5px;
                transition: background-color 0.3s ease;
            }

            .back-button a:hover {
                background-color: #0056b3; 
            }


            body::before {
                content: '';
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                z-index: -1;
                background: url('images/f15697736.jpg') no-repeat center center fixed;
                background-size: cover;
                filter: blur(3px);
            }
            .back-button {
                position: absolute;
                top: 20px; 
                left: 20px; 
            }

            .back-button a {
                display: inline-block;
                background-color: #007bff;
                color: #fff; 
                padding: 10px 20px;
                text-decoration: none;
                border-radius: 5px;
                transition: background-color 0.3s ease;
            }

            .back-button a:hover {
                background-color: #0056b3; 
            }


            .gallery-container {
                max-width: 800px;
                margin: 0 auto;
                position: relative;
                overflow: hidden;
            }

            .slideshow {
                display: flex;
                transition: transform 0.5s ease-in-out;
            }

            .gallery-item {
                flex: 0 0 100%;
                overflow: hidden;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                transition: transform 0.3s ease;
            }

            .gallery-item img {
                width: 100%;
                height: 450px; 
                object-fit: cover;
                object-position: center;
            }

            .gallery-caption {
                padding: 10px;
                background-color: rgba(255, 255, 255, 0.8);
                text-align: center;
            }

            .gallery-caption h3 {
                margin: 5px 0;
                font-size: 1.2rem;
            }

            .gallery-caption p {
                margin: 0;
                font-size: 0.9rem;
                color: #666;
            }

            .arrow {
                position: absolute;
                top: 50%;
                transform: translateY(-50%);
                font-size: 2rem;
                cursor: pointer;
                color: #333;
                z-index: 1;
            }

            .arrow.left {
                left: 10px;
            }

            .arrow.right {
                right: 10px;
            }
        </style>
    </head>
    <body>
  
        <div class="gallery-container">
            <div class="slideshow">
                <div class="gallery-item">
                    <img src="images/f15716520.jpg" alt="Room 1">
                    <div class="gallery-caption">
                        <h3>Standard</h3>
                        <p>Rate: R500 per hour<br>
                            Our standard rooms offer comfort and convenience at an affordable rate.<br>
                            Perfect for short stays or quick getaways, these rooms are designed with your comfort in mind. <br>
                            Whether you're traveling for business or leisure, our standard rooms provide a cozy retreat with essential amenities to ensure a pleasant stay.</p>
                    </div>
                </div>
                <div class="gallery-item">
                    <img src="images/f15841832.jpg" alt="Room 2">
                    <div class="gallery-caption">
                        <h3>Deluxe</h3>
                        <p>Rate: R1000 per hour<br>
                            Our deluxe rooms offer a premium experience with luxurious amenities and elegant decor.<br>
                            Designed for discerning guests seeking comfort and style, these rooms feature spacious layouts and modern conveniences.<br>
                            Whether you're traveling for business or treating yourself to a special getaway, our deluxe rooms provide an exceptional stay with personalized service and attention to detail.</p>
                    </div>
                </div>
                <div class="gallery-item">
                    <img src="images/R.jpg" alt="Room 3">
                    <div class="gallery-caption">
                        <h3>Suite</h3>
                        <p>Rate: R2500 per hour<br>
                            Our Family Suites provide ample space and comfort for families traveling together. <br>
                            Featuring separate bedrooms and living areas, these suites are designed to accommodate families while offering privacy and convenience.</p>
                    </div>
                </div>
                <div class="gallery-item">
                    <img src="images/PR.jpg" alt="Room 4">
                    <div class="gallery-caption">
                        <h3>Presidential Suite</h3>
                        <p>Rate: R5000 per hour<br>
                            Indulge in opulence and grandeur with our Presidential Suite. <br>
                            This exclusive accommodation boasts luxurious amenities, expansive living spaces, and panoramic views. <br>
                            Perfect for VIP guests or those celebrating special occasions.</p>
                    </div>
                </div>
                <div class="gallery-item">
                    <img src="images/pent.jpg" alt="Room 5">
                    <div class="gallery-caption">
                        <h3>Penthouse</h3>
                        <p>Rate: R10000 per hour<br>
                            Our Penthouse offers the ultimate in luxury living. <br>
                            With modern design, top-of-the-line amenities, and breathtaking views, <br>
                            the Penthouse provides an unforgettable experience for guests seeking the finest accommodations.</p>
                    </div>
                </div>
                <div class="gallery-item">
                    <img src="images/jus42.jpg" alt="Room 6">
                    <div class="gallery-caption">
                        <h3>Just 4 Two</h3>
                        <p>Rate: R3000 per hour<br>
                            Escape to our intimate "Just for Two" room, designed exclusively for couples seeking a private retreat. <br>
                            Featuring luxurious amenities and a romantic ambiance. <br>
                            Enjoy a secluded space away from the hustle and bustle, perfect for couples wanting uninterrupted quality time.</p>
                    </div>
                </div>
            </div>
            <div class="arrow left" onclick="moveSlide('left')">&#10094;</div>
            <div class="arrow right" onclick="moveSlide('right')">&#10095;</div>
        </div>

        <script>
            let slideIndex = 1;
            showSlide(slideIndex);

            function moveSlide(direction) {
                if (direction === 'left') {
                    showSlide(slideIndex -= 1);
                } else if (direction === 'right') {
                    showSlide(slideIndex += 1);
                }
            }

            function showSlide(n) {
                const slides = document.getElementsByClassName("gallery-item");
                if (n > slides.length) {
                    slideIndex = 1;
                }
                if (n < 1) {
                    slideIndex = slides.length;
                }
                for (let i = 0; i < slides.length; i++) {
                    slides[i].style.display = "none";
                }
                slides[slideIndex - 1].style.display = "block";
            }
        </script>
    </body>
</html>
