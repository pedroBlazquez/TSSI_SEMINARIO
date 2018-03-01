import moment from 'moment';

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

export function formatFecha (fecha, format = 'DD-MM-YYYY') {
  const f = moment(fecha);
  return f.isValid() ? f.format(format) : fecha;
}