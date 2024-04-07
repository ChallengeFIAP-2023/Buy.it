import { Flex } from '@global/styles';
import { TextIndicator } from '@screens/QuotesHistory/QuotesList/styles';
import styled from 'styled-components/native';

export const Header = styled.View`
  justify-content: center;
  align-items: center;
  display: flex;
  flex-direction: row;
  gap: 10px;
  padding: 0 30px;
  margin: 30px 0;
`;

export const UserInfo = styled.View`
  display: flex;
  flex-direction: column;
  gap: 5px;
  flex: 4;
`;

export const AvatarWrapper = styled.View`
  flex: 2;
`;

export const UserName = styled.Text`
  color: ${({ theme }) => theme.COLORS.WHITE};
  font-family: ${({ theme }) => theme.FONT_FAMILY.RALEWAY.BOLD};
  font-size: ${({ theme }) => theme.FONT_SIZE.XL}px;
`;

export const UserCNPJ = styled.Text`
  color: ${({ theme }) => theme.COLORS.GRAY_300};
  font-family: ${({ theme }) => theme.FONT_FAMILY.ROBOTO.BOLD};
  font-size: ${({ theme }) => theme.FONT_SIZE.SM}px;
`;

export const Title = styled(UserName)`
  margin-top: 30px;  
  margin-bottom: 10px;
`;

export const QuotesWrapper = styled.View`
  background-color: ${({ theme }) => theme.COLORS.GRAY_500};
  padding: 16px;
  border-radius: 10px;
`;

export const Indicator = styled(TextIndicator)`
  margin: 30px 0;
`;

export const Actions = styled(Flex)`
  margin-top: 20px;
`;