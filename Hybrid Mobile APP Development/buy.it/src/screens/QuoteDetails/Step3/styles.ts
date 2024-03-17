import Slider from '@react-native-community/slider';
import styled from 'styled-components/native';

export const LightText = styled.Text`
  color: ${({ theme }) => theme.COLORS.GRAY_300};
  font-size: ${({ theme }) => theme.FONT_SIZE.MD}px;
  font-family: ${({ theme }) => theme.FONT_FAMILY.ROBOTO.REGULAR};

  margin-top: 15px;

  text-align: left;
`;

export const LightBoldText = styled.Text`
  font-family: ${({ theme }) => theme.FONT_FAMILY.ROBOTO.BOLD};
`;

export const Label = styled.Text`
  color: ${({ theme }) => theme.COLORS.WHITE};
  font-size: ${({ theme }) => theme.FONT_SIZE.LG}px;
  font-family: ${({ theme }) => theme.FONT_FAMILY.RALEWAY.BOLD};

  margin-top: 30px;
  margin-bottom: 12px;

  text-align: left;
`;

export const Value = styled.Text`
  color: ${({ theme }) => theme.COLORS.GRAY_300};
  font-size: ${({ theme }) => theme.FONT_SIZE.SM}px;
  font-family: ${({ theme }) => theme.FONT_FAMILY.ROBOTO.REGULAR};
`;

export const CustomSlider = styled(Slider)`
  margin-top: 0;
  margin-bottom: 15px;
`;
