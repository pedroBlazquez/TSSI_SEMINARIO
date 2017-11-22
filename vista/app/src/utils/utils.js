export function agregarArtista (records, artista) {
  if (Array.isArray(records)) {
    return records.map(r => ({...r, artista}));
  } else {
    return records;
  }
}