import { Input } from '@components/Input';
import styled from 'styled-components/native';

export const Container = styled.View`
  padding: 0 35px;
`;

export const CheckBoxContainer = styled.View`
  padding: 0 25px;
`;

export const Subtitle = styled.Text`
  color: ${({ theme }) => theme.COLORS.GRAY_100};
  font-size: ${({ theme }) => theme.FONT_SIZE.LG}px;
  font-family: ${({ theme }) => theme.FONT_FAMILY.RALEWAY.SEMIBOLD};
  margin: 10px 0;
`;