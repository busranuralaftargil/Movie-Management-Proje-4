import { useState, useEffect } from 'react'
import axios from 'axios'

function App() {
  const [movies, setMovies] = useState([])

  // Sayfa yüklendiğinde Spring Boot'tan filmleri çek (GET)
  useEffect(() => {
    fetchMovies();
  }, [])

  const fetchMovies = () => {
    axios.get('http://localhost:8080/movie/all')
      .then(response => {
        setMovies(response.data);
      })
      .catch(error => console.error("Filmler çekilirken hata oluştu:", error));
  }

  // PDF İndirme Butonu Fonksiyonu
  const handlePdfDownload = () => {
    window.open('http://localhost:8080/movie/report', '_blank');
  }

  // Resim Yükleme Fonksiyonu (POST)
  const handleImageUpload = (id, file) => {
    if (!file) return;

    const formData = new FormData();
    formData.append("file", file);

    axios.post(`http://localhost:8080/movie/${id}/uploadImage`, formData, {
      headers: { 'Content-Type': 'multipart/form-data' }
    })
    .then(response => {
      alert("Resim başarıyla yüklendi!");
      fetchMovies(); // Resmi ekranda görebilmek için listeyi yeniliyoruz
    })
    .catch(error => {
      alert("Resim yüklenirken bir hata oluştu.");
      console.error(error);
    });
  }

  return (
    <div style={{ padding: '40px', fontFamily: 'sans-serif', maxWidth: '1000px', margin: '0 auto' }}>

      <h1 style={{ textAlign: 'center', marginBottom: '30px' }}>Movie Management System</h1>

      <div style={{ display: 'flex', justifyContent: 'flex-end', marginBottom: '20px' }}>
        <button
          onClick={handlePdfDownload}
          style={{
            padding: '10px 20px',
            backgroundColor: '#000',
            color: '#fff',
            border: 'none',
            cursor: 'pointer',
            fontWeight: 'bold'
          }}>
          PDF Raporu İndir
        </button>
      </div>

      <table style={{ width: '100%', borderCollapse: 'collapse', textAlign: 'left' }}>
        <thead>
          <tr style={{ borderBottom: '2px solid #000' }}>
            <th style={{ padding: '12px' }}>ID</th>
            <th style={{ padding: '12px' }}>Afiş</th>
            <th style={{ padding: '12px' }}>Title (Başlık)</th>
            <th style={{ padding: '12px' }}>Director (Yönetmen)</th>
            <th style={{ padding: '12px' }}>Year (Yıl)</th>
            <th style={{ padding: '12px' }}>Afiş Yükle</th>
          </tr>
        </thead>
        <tbody>
          {movies.length === 0 ? (
            <tr>
              <td colSpan="6" style={{ textAlign: 'center', padding: '20px' }}>
                Veritabanında henüz film bulunmuyor.
              </td>
            </tr>
          ) : (
            movies.map(movie => (
              <tr key={movie.id} style={{ borderBottom: '1px solid #ddd' }}>
                <td style={{ padding: '12px' }}>{movie.id}</td>
                <td style={{ padding: '12px' }}>
                  {/* Resmi Backend'den alıp gösteriyoruz */}
                  <img
                    src={`http://localhost:8080/movie/${movie.id}/image`}
                    alt={movie.title}
                    style={{ width: '60px', height: '90px', objectFit: 'cover', border: '1px solid #eee' }}
                    onError={(e) => { e.target.src = 'https://via.placeholder.com/60x90?text=Resim+Yok' }}
                  />
                </td>
                <td style={{ padding: '12px', fontWeight: 'bold' }}>{movie.title}</td>
                <td style={{ padding: '12px' }}>{movie.director}</td>
                <td style={{ padding: '12px' }}>{movie.year}</td>
                <td style={{ padding: '12px' }}>
                  <input
                    type="file"
                    accept="image/*"
                    onChange={(e) => handleImageUpload(movie.id, e.target.files[0])}
                  />
                </td>
              </tr>
            ))
          )}
        </tbody>
      </table>
    </div>
  )
}

export default App