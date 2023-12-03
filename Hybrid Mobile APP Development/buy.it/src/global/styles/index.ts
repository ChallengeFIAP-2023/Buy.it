import styled from 'styled-components/native';

export const ScrollableContent = styled.ScrollView`
  padding-top: 20px;

  width: 100%;
  height: 100%;

  background-color: ${({ theme }) => theme.COLORS.GRAY_700};
`;

export const AlertText = styled.Text`
  color: ${({ theme }) => theme.COLORS.GRAY_300};
  font-family: ${({ theme }) => theme.FONT_FAMILY.ROBOTO.REGULAR};
  font-size: ${({ theme }) => theme.FONT_SIZE.SM}px;

  margin-bottom: 30px;
`
