import { User } from "@dtos/user"
import { User as ProposalUser } from "@dtos/proposal"
import { CompanyAvatar, CompanyData, CompanyDocument, CompanyName, CompanyWrapper } from "./styles"
import { ImageSourcePropType } from "react-native";
import { toMaskedCNPJ } from "@utils/masks";
import { UserAvatar } from "@components/UserAvatar";

interface Props {
  user?: User | ProposalUser;
}

export const UserInfo = ({ user }: Props) => {

  const imageSource: ImageSourcePropType = user?.urlImagem
    ? { uri: user?.urlImagem }
    : require('../../assets/default_avatar.png');

  return(
    <CompanyWrapper>
      <CompanyAvatar>
        <UserAvatar imageSource={imageSource} size="SM" />
      </CompanyAvatar>
      <CompanyData>
        <CompanyName numberOfLines={1}>
          {user?.nome ?? 'Empresa não identificada'}
        </CompanyName>
        <CompanyDocument>
          {toMaskedCNPJ(user?.cnpj ?? '')}
        </CompanyDocument>
      </CompanyData>
    </CompanyWrapper>
  )
}