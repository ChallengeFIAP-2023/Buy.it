import styled from 'styled-components/native';
import theme from '@theme/index';
import { TextStyle, ViewStyle } from 'react-native';

export const Label = styled.Text`
  color: ${({ theme }) => theme.COLORS.WHITE};
  font-size: ${({ theme }) => theme.FONT_SIZE.LG}px;
  font-family: ${({ theme }) => theme.FONT_FAMILY.RALEWAY.BOLD};
  margin-bottom: 12px;
`;

export const dropdownStyle: ViewStyle = {
  backgroundColor: theme.COLORS.GRAY_500,
  paddingHorizontal: 12,
  // TODO: esse padding vertical por algum motivo não funciona. Fica maior que o padrão. Precisamos investigar
  paddingVertical: 14,
  borderRadius: 8,
  borderWidth: 0,
  height: 'auto',
  display: 'flex',
  flexDirection: 'row',
};

export const textStyle: TextStyle = {
  color: theme.COLORS.GRAY_200,
  fontFamily: theme.FONT_FAMILY.ROBOTO.REGULAR,
};

export const checkboxStyle: ViewStyle = {
  borderWidth: 0,
};

export const searchInputStyle: TextStyle = {
  backgroundColor: theme.COLORS.GRAY_400,
  borderRadius: 8,
  borderWidth: 0,
  height: 'auto',
  color: theme.COLORS.WHITE,
};
