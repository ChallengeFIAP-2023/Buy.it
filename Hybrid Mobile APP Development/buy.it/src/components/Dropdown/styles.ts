import styled from 'styled-components/native';
import theme from '@theme/index';
import { TextStyle, ViewStyle } from 'react-native';

export const Label = styled.Text`
  color: ${({ theme }) => theme.COLORS.WHITE};
  font-size: ${({ theme }) => theme.FONT_SIZE.LG}px;
  font-family: ${({ theme }) => theme.FONT_FAMILY.RALEWAY.BOLD};
  margin-bottom: 12px;
`;

export const errorStyle: TextStyle = {
  color: theme.COLORS.RED,
  fontSize: theme.FONT_SIZE.SM,
  fontFamily: theme.FONT_FAMILY.RALEWAY.BOLD,
  margin: 5,
};

export const dropdownStyle: ViewStyle = {
  backgroundColor: theme.COLORS.GRAY_500,
  paddingHorizontal: 12,
  borderRadius: 8,
  borderWidth: 0,
};

export const modalOptions: ViewStyle = {
  backgroundColor: theme.COLORS.GRAY_500,
  height: 'auto',
  minHeight: 300,

  display: 'flex',
  flexDirection: 'column',
  justifyContent: 'space-between',
};

export const dropdownErrorStyle: ViewStyle = {
  borderColor: theme.COLORS.RED,
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
