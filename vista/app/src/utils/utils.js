export function agregarArtista (records, artista) {
  if (Array.isArray(records)) {
    return records.map(r => ({...r, artista}));
  } else {
    return records;
  }
}

export function mapContentName (content, excludeId) {
  return content.reduce((acc, curr) => {
    if (curr.id !== excludeId) {
      acc.push(curr.nombre);
    }
    return acc;
  }, []);
}