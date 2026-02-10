'use client'

const images = [
    '/1.png',
    '/2.png',
    '/3.png',
    '/4.png',
    '/5.png',
    '/6.png',
    '/7.png',
    '/8.png',
];

export default function Masonry(){
    return(
        <div className="columns-2 sm:columns-3 lg:columns-5 py-10 md:py-20 gap-4">
            {images.map((src, index) => (
                <div key={index} className="mb-4 breake-inside-avoid">
                    <img src={src} className="w-full object-cover rounded-lg"/>
                </div>
            ))}
        </div>
    )
}