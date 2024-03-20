import styled from 'styled-components/native';

export const LightText = styled.Text`
  color: ${({ theme }) => theme.COLORS.GRAY_300};
  font-size: ${({ theme }) => theme.FONT_SIZE.MD}px;
  font-family: ${({ theme }) => theme.FONT_FAMILY.ROBOTO.REGULAR};

  margin-top: 5px;
  margin-bottom: 15px;

  text-align: left;
`;

export const LightBoldText = styled.Text`
  font-family: ${({ theme }) => theme.FONT_FAMILY.ROBOTO.BOLD};
`;
