import { CaretLeft } from 'phosphor-react-native';
import styled from 'styled-components/native';

// Asset import
import LogoImage from "@assets/logo.png"

export const Container = styled.View`
  width: 100%;

  background-color: ${({ theme }) => theme.COLORS.GRAY_700};

  flex-direction: row;
  align-items: center;
  justify-content: center;
`;

export const Logo = styled.Image.attrs({
  source: LogoImage
})`
  width: 40px;
  height: 45px;
`;

export const BackButton = styled.TouchableOpacity`
  flex: 1;
`;

export const BackIcon = styled(CaretLeft).attrs(({ theme }) => ({
  size: 36,
  color: theme.COLORS.WHITE
}))``;
