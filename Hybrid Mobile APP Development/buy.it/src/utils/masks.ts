import { MaskService } from 'react-native-masked-text';

export function unMask(value: string) {
  return value.replace(/[^\d]/g, '');
}

export function unMaskCurrency(value: string) {
  return Number(value.replace('R$', '').replace(',', '.'));
}

export function toMaskedPhone(value: string) {
  try {
    return MaskService.toMask('cel-phone', value, {
      maskType: 'BRL',
      withDDD: true,
      dddMask: '(99) ',
    });
  } catch (error) {
    throw error;
  }
}

export function toMaskedCNPJ(value: string) {
  try {
    return MaskService.toMask('cnpj', value);
  } catch (error) {
    throw error;
  }
}

export function toMaskedCurrency(value: number, showUnit?: boolean) {
  const formattedNumber = value.toFixed(2).toString();
  try {
    return MaskService.toMask('money', formattedNumber, {
      precision: 2,
      separator: ',',
      unit: showUnit ? 'R$' : '',
    });
  } catch (error) {
    throw error;
  }
}
